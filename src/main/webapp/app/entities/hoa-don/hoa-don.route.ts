import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { HoaDon } from 'app/shared/model/hoa-don.model';
import { HoaDonService } from './hoa-don.service';
import { HoaDonComponent } from './hoa-don.component';
import { HoaDonDetailComponent } from './hoa-don-detail.component';
import { HoaDonUpdateComponent } from './hoa-don-update.component';
import { HoaDonDeletePopupComponent } from './hoa-don-delete-dialog.component';
import { IHoaDon } from 'app/shared/model/hoa-don.model';

@Injectable({ providedIn: 'root' })
export class HoaDonResolve implements Resolve<IHoaDon> {
    constructor(private service: HoaDonService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<HoaDon> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<HoaDon>) => response.ok),
                map((hoaDon: HttpResponse<HoaDon>) => hoaDon.body)
            );
        }
        return of(new HoaDon());
    }
}

export const hoaDonRoute: Routes = [
    {
        path: 'hoa-don',
        component: HoaDonComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'bookDemoApp.hoaDon.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'hoa-don/:id/view',
        component: HoaDonDetailComponent,
        resolve: {
            hoaDon: HoaDonResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bookDemoApp.hoaDon.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'hoa-don/new',
        component: HoaDonUpdateComponent,
        resolve: {
            hoaDon: HoaDonResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bookDemoApp.hoaDon.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'hoa-don/:id/edit',
        component: HoaDonUpdateComponent,
        resolve: {
            hoaDon: HoaDonResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bookDemoApp.hoaDon.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const hoaDonPopupRoute: Routes = [
    {
        path: 'hoa-don/:id/delete',
        component: HoaDonDeletePopupComponent,
        resolve: {
            hoaDon: HoaDonResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bookDemoApp.hoaDon.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
