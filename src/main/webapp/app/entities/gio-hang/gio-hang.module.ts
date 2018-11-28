import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BookDemoSharedModule } from 'app/shared';
import {
    GioHangComponent,
    GioHangDetailComponent,
    GioHangUpdateComponent,
    GioHangDeletePopupComponent,
    GioHangDeleteDialogComponent,
    gioHangRoute,
    gioHangPopupRoute
} from './';

const ENTITY_STATES = [...gioHangRoute, ...gioHangPopupRoute];

@NgModule({
    imports: [BookDemoSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        GioHangComponent,
        GioHangDetailComponent,
        GioHangUpdateComponent,
        GioHangDeleteDialogComponent,
        GioHangDeletePopupComponent
    ],
    entryComponents: [GioHangComponent, GioHangUpdateComponent, GioHangDeleteDialogComponent, GioHangDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class BookDemoGioHangModule {}
