import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { NhaXuatBan } from 'app/shared/model/nha-xuat-ban.model';
import { NhaXuatBanService } from './nha-xuat-ban.service';
import { NhaXuatBanComponent } from './nha-xuat-ban.component';
import { NhaXuatBanDetailComponent } from './nha-xuat-ban-detail.component';
import { NhaXuatBanUpdateComponent } from './nha-xuat-ban-update.component';
import { NhaXuatBanDeletePopupComponent } from './nha-xuat-ban-delete-dialog.component';
import { INhaXuatBan } from 'app/shared/model/nha-xuat-ban.model';

@Injectable({ providedIn: 'root' })
export class NhaXuatBanResolve implements Resolve<INhaXuatBan> {
    constructor(private service: NhaXuatBanService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<NhaXuatBan> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<NhaXuatBan>) => response.ok),
                map((nhaXuatBan: HttpResponse<NhaXuatBan>) => nhaXuatBan.body)
            );
        }
        return of(new NhaXuatBan());
    }
}

export const nhaXuatBanRoute: Routes = [
    {
        path: 'nha-xuat-ban',
        component: NhaXuatBanComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'bookDemoApp.nhaXuatBan.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'nha-xuat-ban/:id/view',
        component: NhaXuatBanDetailComponent,
        resolve: {
            nhaXuatBan: NhaXuatBanResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bookDemoApp.nhaXuatBan.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'nha-xuat-ban/new',
        component: NhaXuatBanUpdateComponent,
        resolve: {
            nhaXuatBan: NhaXuatBanResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bookDemoApp.nhaXuatBan.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'nha-xuat-ban/:id/edit',
        component: NhaXuatBanUpdateComponent,
        resolve: {
            nhaXuatBan: NhaXuatBanResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bookDemoApp.nhaXuatBan.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const nhaXuatBanPopupRoute: Routes = [
    {
        path: 'nha-xuat-ban/:id/delete',
        component: NhaXuatBanDeletePopupComponent,
        resolve: {
            nhaXuatBan: NhaXuatBanResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bookDemoApp.nhaXuatBan.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
