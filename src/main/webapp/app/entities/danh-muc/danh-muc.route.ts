import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { DanhMuc } from 'app/shared/model/danh-muc.model';
import { DanhMucService } from './danh-muc.service';
import { DanhMucComponent } from './danh-muc.component';
import { DanhMucDetailComponent } from './danh-muc-detail.component';
import { DanhMucUpdateComponent } from './danh-muc-update.component';
import { DanhMucDeletePopupComponent } from './danh-muc-delete-dialog.component';
import { IDanhMuc } from 'app/shared/model/danh-muc.model';

@Injectable({ providedIn: 'root' })
export class DanhMucResolve implements Resolve<IDanhMuc> {
    constructor(private service: DanhMucService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<DanhMuc> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<DanhMuc>) => response.ok),
                map((danhMuc: HttpResponse<DanhMuc>) => danhMuc.body)
            );
        }
        return of(new DanhMuc());
    }
}

export const danhMucRoute: Routes = [
    {
        path: 'danh-muc',
        component: DanhMucComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'bookDemoApp.danhMuc.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'danh-muc/:id/view',
        component: DanhMucDetailComponent,
        resolve: {
            danhMuc: DanhMucResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bookDemoApp.danhMuc.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'danh-muc/new',
        component: DanhMucUpdateComponent,
        resolve: {
            danhMuc: DanhMucResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bookDemoApp.danhMuc.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'danh-muc/:id/edit',
        component: DanhMucUpdateComponent,
        resolve: {
            danhMuc: DanhMucResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bookDemoApp.danhMuc.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const danhMucPopupRoute: Routes = [
    {
        path: 'danh-muc/:id/delete',
        component: DanhMucDeletePopupComponent,
        resolve: {
            danhMuc: DanhMucResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bookDemoApp.danhMuc.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
