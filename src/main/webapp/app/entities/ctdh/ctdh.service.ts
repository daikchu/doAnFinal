import { Injectable } from '@angular/core';
import { HttpClient, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { SERVER_API_URL } from 'app/app.constants';
import { createRequestOption } from 'app/shared';
import { ICTDH } from 'app/shared/model/ctdh.model';

type EntityResponseType = HttpResponse<ICTDH>;
type EntityArrayResponseType = HttpResponse<ICTDH[]>;

@Injectable({ providedIn: 'root' })
export class CTDHService {
    public resourceUrl = SERVER_API_URL + 'api/ctdhs';

    constructor(private http: HttpClient) {}

    create(cTDH: ICTDH): Observable<EntityResponseType> {
        return this.http.post<ICTDH>(this.resourceUrl, cTDH, { observe: 'response' });
    }

    update(cTDH: ICTDH): Observable<EntityResponseType> {
        return this.http.put<ICTDH>(this.resourceUrl, cTDH, { observe: 'response' });
    }

    find(id: string): Observable<EntityResponseType> {
        return this.http.get<ICTDH>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }

    query(req?: any): Observable<EntityArrayResponseType> {
        const options = createRequestOption(req);
        return this.http.get<ICTDH[]>(this.resourceUrl, { params: options, observe: 'response' });
    }

    delete(id: string): Observable<HttpResponse<any>> {
        return this.http.delete<any>(`${this.resourceUrl}/${id}`, { observe: 'response' });
    }
}
