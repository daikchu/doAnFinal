import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { ICTDH } from 'app/shared/model/ctdh.model';
import { CTDHService } from './ctdh.service';

@Component({
    selector: 'jhi-ctdh-update',
    templateUrl: './ctdh-update.component.html'
})
export class CTDHUpdateComponent implements OnInit {
    cTDH: ICTDH;
    isSaving: boolean;

    constructor(private cTDHService: CTDHService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ cTDH }) => {
            this.cTDH = cTDH;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.cTDH.id !== undefined) {
            this.subscribeToSaveResponse(this.cTDHService.update(this.cTDH));
        } else {
            this.subscribeToSaveResponse(this.cTDHService.create(this.cTDH));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<ICTDH>>) {
        result.subscribe((res: HttpResponse<ICTDH>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
