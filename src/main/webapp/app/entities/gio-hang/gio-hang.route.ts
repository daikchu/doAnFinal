import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { GioHang } from 'app/shared/model/gio-hang.model';
import { GioHangService } from './gio-hang.service';
import { GioHangComponent } from './gio-hang.component';
import { GioHangDetailComponent } from './gio-hang-detail.component';
import { GioHangUpdateComponent } from './gio-hang-update.component';
import { GioHangDeletePopupComponent } from './gio-hang-delete-dialog.component';
import { IGioHang } from 'app/shared/model/gio-hang.model';

@Injectable({ providedIn: 'root' })
export class GioHangResolve implements Resolve<IGioHang> {
    constructor(private service: GioHangService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<GioHang> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<GioHang>) => response.ok),
                map((gioHang: HttpResponse<GioHang>) => gioHang.body)
            );
        }
        return of(new GioHang());
    }
}

export const gioHangRoute: Routes = [
    {
        path: 'gio-hang',
        component: GioHangComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'bookDemoApp.gioHang.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'gio-hang/:id/view',
        component: GioHangDetailComponent,
        resolve: {
            gioHang: GioHangResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bookDemoApp.gioHang.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'gio-hang/new',
        component: GioHangUpdateComponent,
        resolve: {
            gioHang: GioHangResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bookDemoApp.gioHang.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'gio-hang/:id/edit',
        component: GioHangUpdateComponent,
        resolve: {
            gioHang: GioHangResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bookDemoApp.gioHang.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const gioHangPopupRoute: Routes = [
    {
        path: 'gio-hang/:id/delete',
        component: GioHangDeletePopupComponent,
        resolve: {
            gioHang: GioHangResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bookDemoApp.gioHang.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
