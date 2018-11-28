import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IHoaDon } from 'app/shared/model/hoa-don.model';
import { HoaDonService } from './hoa-don.service';

@Component({
    selector: 'jhi-hoa-don-update',
    templateUrl: './hoa-don-update.component.html'
})
export class HoaDonUpdateComponent implements OnInit {
    hoaDon: IHoaDon;
    isSaving: boolean;
    ngayLap: string;

    constructor(private hoaDonService: HoaDonService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ hoaDon }) => {
            this.hoaDon = hoaDon;
            this.ngayLap = this.hoaDon.ngayLap != null ? this.hoaDon.ngayLap.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.hoaDon.ngayLap = this.ngayLap != null ? moment(this.ngayLap, DATE_TIME_FORMAT) : null;
        if (this.hoaDon.id !== undefined) {
            this.subscribeToSaveResponse(this.hoaDonService.update(this.hoaDon));
        } else {
            this.subscribeToSaveResponse(this.hoaDonService.create(this.hoaDon));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IHoaDon>>) {
        result.subscribe((res: HttpResponse<IHoaDon>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
