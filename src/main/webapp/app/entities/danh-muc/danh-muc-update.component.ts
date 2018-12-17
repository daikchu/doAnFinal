import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { IDanhMuc } from 'app/shared/model/danh-muc.model';
import { DanhMucService } from './danh-muc.service';

@Component({
    selector: 'jhi-danh-muc-update',
    templateUrl: './danh-muc-update.component.html'
})
export class DanhMucUpdateComponent implements OnInit {
    danhMuc: IDanhMuc;
    isSaving: boolean;

    constructor(private danhMucService: DanhMucService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ danhMuc }) => {
            this.danhMuc = danhMuc;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.danhMuc.id !== undefined) {
            this.subscribeToSaveResponse(this.danhMucService.update(this.danhMuc));
        } else {
            this.subscribeToSaveResponse(this.danhMucService.create(this.danhMuc));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IDanhMuc>>) {
        result.subscribe((res: HttpResponse<IDanhMuc>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
