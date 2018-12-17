import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { IDanhMuc } from 'app/shared/model/danh-muc.model';

type EntityResponseType = HttpResponse<IDanhMuc>;
type EntityArrayResponseType = HttpResponse<IDanhMuc[]>;

@Injectable({ providedIn: 'root' })
export class DanhMucService {
    public resourceUrl = SERVER_API_URL + 'api/danh-mucs';

    constructor(private http: HttpClient) {}

    create(danhMuc: IDanhMuc): Observable<EntityResponseType> {
        return this.http.post<IDanhMuc>(this.resourceUrl, danhMuc, { observe: 'response' });
    }

    update(danhMuc: IDanhMuc): Observable<EntityResponseType> {
        return this.http.put<IDanhMuc>(this.resourceUrl, danhMuc, { observe: 'response' });
    }

    find(id: string): Observable<EntityResponseType> {
        return this.http.get<IDanhMuc>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<IDanhMuc[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: string): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
