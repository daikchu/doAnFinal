import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BookDemoSharedModule } from 'app/shared';
import {
    DanhMucComponent,
    DanhMucDetailComponent,
    DanhMucUpdateComponent,
    DanhMucDeletePopupComponent,
    DanhMucDeleteDialogComponent,
    danhMucRoute,
    danhMucPopupRoute
} from './';

const ENTITY_STATES = [...danhMucRoute, ...danhMucPopupRoute];

@NgModule({
    imports: [BookDemoSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        DanhMucComponent,
        DanhMucDetailComponent,
        DanhMucUpdateComponent,
        DanhMucDeleteDialogComponent,
        DanhMucDeletePopupComponent
    ],
    entryComponents: [DanhMucComponent, DanhMucUpdateComponent, DanhMucDeleteDialogComponent, DanhMucDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class BookDemoDanhMucModule {}
