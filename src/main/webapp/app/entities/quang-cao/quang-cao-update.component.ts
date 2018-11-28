import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';
import * as moment from 'moment';
import { DATE_TIME_FORMAT } from 'app/shared/constants/input.constants';

import { IQuangCao } from 'app/shared/model/quang-cao.model';
import { QuangCaoService } from './quang-cao.service';

@Component({
    selector: 'jhi-quang-cao-update',
    templateUrl: './quang-cao-update.component.html'
})
export class QuangCaoUpdateComponent implements OnInit {
    quangCao: IQuangCao;
    isSaving: boolean;
    timeStart: string;
    timeEnd: string;

    constructor(private quangCaoService: QuangCaoService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ quangCao }) => {
            this.quangCao = quangCao;
            this.timeStart = this.quangCao.timeStart != null ? this.quangCao.timeStart.format(DATE_TIME_FORMAT) : null;
            this.timeEnd = this.quangCao.timeEnd != null ? this.quangCao.timeEnd.format(DATE_TIME_FORMAT) : null;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        this.quangCao.timeStart = this.timeStart != null ? moment(this.timeStart, DATE_TIME_FORMAT) : null;
        this.quangCao.timeEnd = this.timeEnd != null ? moment(this.timeEnd, DATE_TIME_FORMAT) : null;
        if (this.quangCao.id !== undefined) {
            this.subscribeToSaveResponse(this.quangCaoService.update(this.quangCao));
        } else {
            this.subscribeToSaveResponse(this.quangCaoService.create(this.quangCao));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<IQuangCao>>) {
        result.subscribe((res: HttpResponse<IQuangCao>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
