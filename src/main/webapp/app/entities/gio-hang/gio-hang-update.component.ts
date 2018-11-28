import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IGioHang } from 'app/shared/model/gio-hang.model';
import { GioHangService } from './gio-hang.service';

@Component({
    selector: 'jhi-gio-hang-update',
    templateUrl: './gio-hang-update.component.html'
})
export class GioHangUpdateComponent implements OnInit {
    gioHang: IGioHang;
    isSaving: boolean;
    dateUpdate: string;

    constructor(private gioHangService: GioHangService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ gioHang }) => {
            this.gioHang = gioHang;
            this.dateUpdate = this.gioHang.dateUpdate != null ? this.gioHang.dateUpdate.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.gioHang.dateUpdate = this.dateUpdate != null ? moment(this.dateUpdate, DATE_TIME_FORMAT) : null;
        if (this.gioHang.id !== undefined) {
            this.subscribeToSaveResponse(this.gioHangService.update(this.gioHang));
        } else {
            this.subscribeToSaveResponse(this.gioHangService.create(this.gioHang));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IGioHang>>) {
        result.subscribe((res: HttpResponse<IGioHang>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
