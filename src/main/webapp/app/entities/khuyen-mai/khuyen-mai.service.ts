import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IKhuyenMai } from 'app/shared/model/khuyen-mai.model';

type EntityResponseType = HttpResponse<IKhuyenMai>;
type EntityArrayResponseType = HttpResponse<IKhuyenMai[]>;

@Injectable({ providedIn: 'root' })
export class KhuyenMaiService {
    public resourceUrl = SERVER_API_URL + 'api/khuyen-mais';

    constructor(private http: HttpClient) {}

    create(khuyenMai: IKhuyenMai): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(khuyenMai);
        return this.http
            .post<IKhuyenMai>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(khuyenMai: IKhuyenMai): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(khuyenMai);
        return this.http
            .put<IKhuyenMai>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: string): Observable<EntityResponseType> {
        return this.http
            .get<IKhuyenMai>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IKhuyenMai[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: string): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(khuyenMai: IKhuyenMai): IKhuyenMai {
        const copy: IKhuyenMai = Object.assign({}, khuyenMai, {
            timeStart: khuyenMai.timeStart != null && khuyenMai.timeStart.isValid() ? khuyenMai.timeStart.toJSON() : null,
            timeEnd: khuyenMai.timeEnd != null && khuyenMai.timeEnd.isValid() ? khuyenMai.timeEnd.toJSON() : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.timeStart = res.body.timeStart != null ? moment(res.body.timeStart) : null;
            res.body.timeEnd = res.body.timeEnd != null ? moment(res.body.timeEnd) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((khuyenMai: IKhuyenMai) => {
                khuyenMai.timeStart = khuyenMai.timeStart != null ? moment(khuyenMai.timeStart) : null;
                khuyenMai.timeEnd = khuyenMai.timeEnd != null ? moment(khuyenMai.timeEnd) : null;
            });
        }
        return res;
    }
}
