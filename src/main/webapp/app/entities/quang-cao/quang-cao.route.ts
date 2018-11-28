import { Injectable } from '@angular/core';
import { HttpResponse } from '@angular/common/http';
import { Resolve, ActivatedRouteSnapshot, RouterStateSnapshot, Routes } from '@angular/router';
import { JhiPaginationUtil, JhiResolvePagingParams } from 'ng-jhipster';
import { UserRouteAccessService } from 'app/core';
import { Observable, of } from 'rxjs';
import { filter, map } from 'rxjs/operators';
import { QuangCao } from 'app/shared/model/quang-cao.model';
import { QuangCaoService } from './quang-cao.service';
import { QuangCaoComponent } from './quang-cao.component';
import { QuangCaoDetailComponent } from './quang-cao-detail.component';
import { QuangCaoUpdateComponent } from './quang-cao-update.component';
import { QuangCaoDeletePopupComponent } from './quang-cao-delete-dialog.component';
import { IQuangCao } from 'app/shared/model/quang-cao.model';

@Injectable({ providedIn: 'root' })
export class QuangCaoResolve implements Resolve<IQuangCao> {
    constructor(private service: QuangCaoService) {}

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<QuangCao> {
        const id = route.params['id'] ? route.params['id'] : null;
        if (id) {
            return this.service.find(id).pipe(
                filter((response: HttpResponse<QuangCao>) => response.ok),
                map((quangCao: HttpResponse<QuangCao>) => quangCao.body)
            );
        }
        return of(new QuangCao());
    }
}

export const quangCaoRoute: Routes = [
    {
        path: 'quang-cao',
        component: QuangCaoComponent,
        resolve: {
            pagingParams: JhiResolvePagingParams
        },
        data: {
            authorities: ['ROLE_USER'],
            defaultSort: 'id,asc',
            pageTitle: 'bookDemoApp.quangCao.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'quang-cao/:id/view',
        component: QuangCaoDetailComponent,
        resolve: {
            quangCao: QuangCaoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bookDemoApp.quangCao.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'quang-cao/new',
        component: QuangCaoUpdateComponent,
        resolve: {
            quangCao: QuangCaoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bookDemoApp.quangCao.home.title'
        },
        canActivate: [UserRouteAccessService]
    },
    {
        path: 'quang-cao/:id/edit',
        component: QuangCaoUpdateComponent,
        resolve: {
            quangCao: QuangCaoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bookDemoApp.quangCao.home.title'
        },
        canActivate: [UserRouteAccessService]
    }
];

export const quangCaoPopupRoute: Routes = [
    {
        path: 'quang-cao/:id/delete',
        component: QuangCaoDeletePopupComponent,
        resolve: {
            quangCao: QuangCaoResolve
        },
        data: {
            authorities: ['ROLE_USER'],
            pageTitle: 'bookDemoApp.quangCao.home.title'
        },
        canActivate: [UserRouteAccessService],
        outlet: 'popup'
    }
];
