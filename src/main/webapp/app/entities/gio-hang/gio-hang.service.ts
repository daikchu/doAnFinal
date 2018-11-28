import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_FORMAT } from 'app/shared/constants/input.constants';
import { map } from 'rxjs/operators';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IGioHang } from 'app/shared/model/gio-hang.model';

type EntityResponseType = HttpResponse<IGioHang>;
type EntityArrayResponseType = HttpResponse<IGioHang[]>;

@Injectable({ providedIn: 'root' })
export class GioHangService {
    public resourceUrl = SERVER_API_URL + 'api/gio-hangs';

    constructor(private http: HttpClient) {}

    create(gioHang: IGioHang): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(gioHang);
        return this.http
            .post<IGioHang>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    update(gioHang: IGioHang): Observable<EntityResponseType> {
        const copy = this.convertDateFromClient(gioHang);
        return this.http
            .put<IGioHang>(this.resourceUrl, copy, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    find(id: string): Observable<EntityResponseType> {
        return this.http
            .get<IGioHang>(`${this.resourceUrl}/${id}`, { observe: 'response' })
            .pipe(map((res: EntityResponseType) => this.convertDateFromServer(res)));
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http
            .get<IGioHang[]>(this.resourceUrl, { params: options, observe: 'response' })
            .pipe(map((res: EntityArrayResponseType) => this.convertDateArrayFromServer(res)));
    }

    delete(id: string): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    protected convertDateFromClient(gioHang: IGioHang): IGioHang {
        const copy: IGioHang = Object.assign({}, gioHang, {
            dateUpdate: gioHang.dateUpdate != null && gioHang.dateUpdate.isValid() ? gioHang.dateUpdate.toJSON() : null
        });
        return copy;
    }

    protected convertDateFromServer(res: EntityResponseType): EntityResponseType {
        if (res.body) {
            res.body.dateUpdate = res.body.dateUpdate != null ? moment(res.body.dateUpdate) : null;
        }
        return res;
    }

    protected convertDateArrayFromServer(res: EntityArrayResponseType): EntityArrayResponseType {
        if (res.body) {
            res.body.forEach((gioHang: IGioHang) => {
                gioHang.dateUpdate = gioHang.dateUpdate != null ? moment(gioHang.dateUpdate) : null;
            });
        }
        return res;
    }
}
