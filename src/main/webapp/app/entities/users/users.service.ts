import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IUsers } from 'app/shared/model/users.model';

type EntityResponseType = HttpResponse<IUsers>;
type EntityArrayResponseType = HttpResponse<IUsers[]>;

@Injectable({ providedIn: 'root' })
export class UsersService {
    public resourceUrl = SERVER_API_URL + 'api/users';

    constructor(private http: HttpClient) {}

    create(users: IUsers): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(users);
        return this.http
            .post<IUsers>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(users: IUsers): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(users);
        return this.http
            .put<IUsers>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: string): Observable<EntityResponseType> {
        return this.http
            .get<IUsers>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IUsers[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: string): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(users: IUsers): IUsers {
        const copy: IUsers = Object.assign({}, users, {
            startDate: users.startDate != null && users.startDate.isValid() ? users.startDate.format(DATE_FORMAT) : null,
            ngaySinh: users.ngaySinh != null && users.ngaySinh.isValid() ? users.ngaySinh.format(DATE_FORMAT) : null,
            dateCreated: users.dateCreated != null && users.dateCreated.isValid() ? users.dateCreated.format(DATE_FORMAT) : null,
            dateUpdated: users.dateUpdated != null && users.dateUpdated.isValid() ? users.dateUpdated.format(DATE_FORMAT) : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.startDate = res.body.startDate != null ? moment(res.body.startDate) : null;
            res.body.ngaySinh = res.body.ngaySinh != null ? moment(res.body.ngaySinh) : null;
            res.body.dateCreated = res.body.dateCreated != null ? moment(res.body.dateCreated) : null;
            res.body.dateUpdated = res.body.dateUpdated != null ? moment(res.body.dateUpdated) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((users: IUsers) => {
                users.startDate = users.startDate != null ? moment(users.startDate) : null;
                users.ngaySinh = users.ngaySinh != null ? moment(users.ngaySinh) : null;
                users.dateCreated = users.dateCreated != null ? moment(users.dateCreated) : null;
                users.dateUpdated = users.dateUpdated != null ? moment(users.dateUpdated) : null;
            });
        }
        return res;
    }
}
