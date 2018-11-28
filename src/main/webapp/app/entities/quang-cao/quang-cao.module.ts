import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { BookDemoSharedModule } from 'app/shared';
import {
    QuangCaoComponent,
    QuangCaoDetailComponent,
    QuangCaoUpdateComponent,
    QuangCaoDeletePopupComponent,
    QuangCaoDeleteDialogComponent,
    quangCaoRoute,
    quangCaoPopupRoute
} from './';

const ENTITY_STATES = [...quangCaoRoute, ...quangCaoPopupRoute];

@NgModule({
    imports: [BookDemoSharedModule, RouterModule.forChild(ENTITY_STATES)],
    declarations: [
        QuangCaoComponent,
        QuangCaoDetailComponent,
        QuangCaoUpdateComponent,
        QuangCaoDeleteDialogComponent,
        QuangCaoDeletePopupComponent
    ],
    entryComponents: [QuangCaoComponent, QuangCaoUpdateComponent, QuangCaoDeleteDialogComponent, QuangCaoDeletePopupComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class BookDemoQuangCaoModule {}
