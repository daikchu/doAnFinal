import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BookDemoSharedModule } from 'app/shared';
import {
    KhuyenMaiComponent,
    KhuyenMaiDetailComponent,
    KhuyenMaiUpdateComponent,
    KhuyenMaiDeletePopupComponent,
    KhuyenMaiDeleteDialogComponent,
    khuyenMaiRoute,
    khuyenMaiPopupRoute
} from './';

const ENTITY_STATES = [...khuyenMaiRoute, ...khuyenMaiPopupRoute];

@NgModule({
    imports: [BookDemoSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        KhuyenMaiComponent,
        KhuyenMaiDetailComponent,
        KhuyenMaiUpdateComponent,
        KhuyenMaiDeleteDialogComponent,
        KhuyenMaiDeletePopupComponent
    ],
    entryComponents: [KhuyenMaiComponent, KhuyenMaiUpdateComponent, KhuyenMaiDeleteDialogComponent, KhuyenMaiDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class BookDemoKhuyenMaiModule {}
