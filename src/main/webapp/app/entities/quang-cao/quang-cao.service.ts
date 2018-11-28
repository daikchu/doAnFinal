import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IQuangCao } from 'app/shared/model/quang-cao.model';

type EntityResponseType = HttpResponse<IQuangCao>;
type EntityArrayResponseType = HttpResponse<IQuangCao[]>;

@Injectable({ providedIn: 'root' })
export class QuangCaoService {
    public resourceUrl = SERVER_API_URL + 'api/quang-caos';

    constructor(private http: HttpClient) {}

    create(quangCao: IQuangCao): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(quangCao);
        return this.http
            .post<IQuangCao>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(quangCao: IQuangCao): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(quangCao);
        return this.http
            .put<IQuangCao>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: string): Observable<EntityResponseType> {
        return this.http
            .get<IQuangCao>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IQuangCao[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: string): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(quangCao: IQuangCao): IQuangCao {
        const copy: IQuangCao = Object.assign({}, quangCao, {
            timeStart: quangCao.timeStart != null && quangCao.timeStart.isValid() ? quangCao.timeStart.toJSON() : null,
            timeEnd: quangCao.timeEnd != null && quangCao.timeEnd.isValid() ? quangCao.timeEnd.toJSON() : null
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
            res.body.forEach((quangCao: IQuangCao) => {
                quangCao.timeStart = quangCao.timeStart != null ? moment(quangCao.timeStart) : null;
                quangCao.timeEnd = quangCao.timeEnd != null ? moment(quangCao.timeEnd) : null;
            });
        }
        return res;
    }
}
