import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { KhuyenMai } from 'app/shared/model/khuyen-mai.model';
import { KhuyenMaiService } from './khuyen-mai.service';
import { KhuyenMaiComponent } from './khuyen-mai.component';
import { KhuyenMaiDetailComponent } from './khuyen-mai-detail.component';
import { KhuyenMaiUpdateComponent } from './khuyen-mai-update.component';
import { KhuyenMaiDeletePopupComponent } from './khuyen-mai-delete-dialog.component';
import { IKhuyenMai } from 'app/shared/model/khuyen-mai.model';

@Injectable({ providedIn: 'root' })
export class KhuyenMaiResolve implements Resolve<IKhuyenMai> {
    constructor(private service: KhuyenMaiService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<KhuyenMai> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<KhuyenMai>) => response.ok),
                map((khuyenMai: HttpResponse<KhuyenMai>) => khuyenMai.body)
            );
        }
        return of(new KhuyenMai());
    }
}

export const khuyenMaiRoute: Routes = [
    {
        path: 'khuyen-mai',
        component: KhuyenMaiComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'bookDemoApp.khuyenMai.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'khuyen-mai/:id/view',
        component: KhuyenMaiDetailComponent,
        resolve: {
            khuyenMai: KhuyenMaiResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bookDemoApp.khuyenMai.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'khuyen-mai/new',
        component: KhuyenMaiUpdateComponent,
        resolve: {
            khuyenMai: KhuyenMaiResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bookDemoApp.khuyenMai.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'khuyen-mai/:id/edit',
        component: KhuyenMaiUpdateComponent,
        resolve: {
            khuyenMai: KhuyenMaiResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bookDemoApp.khuyenMai.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const khuyenMaiPopupRoute: Routes = [
    {
        path: 'khuyen-mai/:id/delete',
        component: KhuyenMaiDeletePopupComponent,
        resolve: {
            khuyenMai: KhuyenMaiResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bookDemoApp.khuyenMai.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
