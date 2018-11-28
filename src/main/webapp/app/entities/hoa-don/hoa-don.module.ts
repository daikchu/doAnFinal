import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BookDemoSharedModule } from 'app/shared';
import {
    HoaDonComponent,
    HoaDonDetailComponent,
    HoaDonUpdateComponent,
    HoaDonDeletePopupComponent,
    HoaDonDeleteDialogComponent,
    hoaDonRoute,
    hoaDonPopupRoute
} from './';

const ENTITY_STATES = [...hoaDonRoute, ...hoaDonPopupRoute];

@NgModule({
    imports: [BookDemoSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [HoaDonComponent, HoaDonDetailComponent, HoaDonUpdateComponent, HoaDonDeleteDialogComponent, HoaDonDeletePopupComponent],
    entryComponents: [HoaDonComponent, HoaDonUpdateComponent, HoaDonDeleteDialogComponent, HoaDonDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class BookDemoHoaDonModule {}
