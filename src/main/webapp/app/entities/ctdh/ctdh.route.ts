import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { CTDH } from 'app/shared/model/ctdh.model';
import { CTDHService } from './ctdh.service';
import { CTDHComponent } from './ctdh.component';
import { CTDHDetailComponent } from './ctdh-detail.component';
import { CTDHUpdateComponent } from './ctdh-update.component';
import { CTDHDeletePopupComponent } from './ctdh-delete-dialog.component';
import { ICTDH } from 'app/shared/model/ctdh.model';

@Injectable({ providedIn: 'root' })
export class CTDHResolve implements Resolve<ICTDH> {
    constructor(private service: CTDHService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<CTDH> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<CTDH>) => response.ok),
                map((cTDH: HttpResponse<CTDH>) => cTDH.body)
            );
        }
        return of(new CTDH());
    }
}

export const cTDHRoute: Routes = [
    {
        path: 'ctdh',
        component: CTDHComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'bookDemoApp.cTDH.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'ctdh/:id/view',
        component: CTDHDetailComponent,
        resolve: {
            cTDH: CTDHResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bookDemoApp.cTDH.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'ctdh/new',
        component: CTDHUpdateComponent,
        resolve: {
            cTDH: CTDHResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bookDemoApp.cTDH.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'ctdh/:id/edit',
        component: CTDHUpdateComponent,
        resolve: {
            cTDH: CTDHResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bookDemoApp.cTDH.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const cTDHPopupRoute: Routes = [
    {
        path: 'ctdh/:id/delete',
        component: CTDHDeletePopupComponent,
        resolve: {
            cTDH: CTDHResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bookDemoApp.cTDH.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
