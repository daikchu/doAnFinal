import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IKhuyenMai } from 'app/shared/model/khuyen-mai.model';
import { KhuyenMaiService } from './khuyen-mai.service';

@Component({
    selector: 'jhi-khuyen-mai-update',
    templateUrl: './khuyen-mai-update.component.html'
})
export class KhuyenMaiUpdateComponent implements OnInit {
    khuyenMai: IKhuyenMai;
    isSaving: boolean;
    timeStart: string;
    timeEnd: string;

    constructor(private khuyenMaiService: KhuyenMaiService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ khuyenMai }) => {
            this.khuyenMai = khuyenMai;
            this.timeStart = this.khuyenMai.timeStart != null ? this.khuyenMai.timeStart.format(DATE_TIME_FORMAT) : null;
            this.timeEnd = this.khuyenMai.timeEnd != null ? this.khuyenMai.timeEnd.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.khuyenMai.timeStart = this.timeStart != null ? moment(this.timeStart, DATE_TIME_FORMAT) : null;
        this.khuyenMai.timeEnd = this.timeEnd != null ? moment(this.timeEnd, DATE_TIME_FORMAT) : null;
        if (this.khuyenMai.id !== undefined) {
            this.subscribeToSaveResponse(this.khuyenMaiService.update(this.khuyenMai));
        } else {
            this.subscribeToSaveResponse(this.khuyenMaiService.create(this.khuyenMai));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IKhuyenMai>>) {
        result.subscribe((res: HttpResponse<IKhuyenMai>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
