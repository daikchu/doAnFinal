import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BookDemoSharedModule } from 'app/shared';
import {
    NhaXuatBanComponent,
    NhaXuatBanDetailComponent,
    NhaXuatBanUpdateComponent,
    NhaXuatBanDeletePopupComponent,
    NhaXuatBanDeleteDialogComponent,
    nhaXuatBanRoute,
    nhaXuatBanPopupRoute
} from './';

const ENTITY_STATES = [...nhaXuatBanRoute, ...nhaXuatBanPopupRoute];

@NgModule({
    imports: [BookDemoSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        NhaXuatBanComponent,
        NhaXuatBanDetailComponent,
        NhaXuatBanUpdateComponent,
        NhaXuatBanDeleteDialogComponent,
        NhaXuatBanDeletePopupComponent
    ],
    entryComponents: [NhaXuatBanComponent, NhaXuatBanUpdateComponent, NhaXuatBanDeleteDialogComponent, NhaXuatBanDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class BookDemoNhaXuatBanModule {}
