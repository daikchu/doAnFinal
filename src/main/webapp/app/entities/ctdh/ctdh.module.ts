import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BookDemoSharedModule } from 'app/shared';
import {
    CTDHComponent,
    CTDHDetailComponent,
    CTDHUpdateComponent,
    CTDHDeletePopupComponent,
    CTDHDeleteDialogComponent,
    cTDHRoute,
    cTDHPopupRoute
} from './';

const ENTITY_STATES = [...cTDHRoute, ...cTDHPopupRoute];

@NgModule({
    imports: [BookDemoSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [CTDHComponent, CTDHDetailComponent, CTDHUpdateComponent, CTDHDeleteDialogComponent, CTDHDeletePopupComponent],
    entryComponents: [CTDHComponent, CTDHUpdateComponent, CTDHDeleteDialogComponent, CTDHDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class BookDemoCTDHModule {}
