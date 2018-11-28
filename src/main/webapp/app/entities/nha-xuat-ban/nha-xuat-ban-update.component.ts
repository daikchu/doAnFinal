import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { HttpResponse, HttpErrorResponse } from '@angular/common/http';
import { Observable } from 'rxjs';

import { INhaXuatBan } from 'app/shared/model/nha-xuat-ban.model';
import { NhaXuatBanService } from './nha-xuat-ban.service';

@Component({
    selector: 'jhi-nha-xuat-ban-update',
    templateUrl: './nha-xuat-ban-update.component.html'
})
export class NhaXuatBanUpdateComponent implements OnInit {
    nhaXuatBan: INhaXuatBan;
    isSaving: boolean;

    constructor(private nhaXuatBanService: NhaXuatBanService, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.isSaving = false;
        this.activatedRoute.data.subscribe(({ nhaXuatBan }) => {
            this.nhaXuatBan = nhaXuatBan;
        });
    }

    previousState() {
        window.history.back();
    }

    save() {
        this.isSaving = true;
        if (this.nhaXuatBan.id !== undefined) {
            this.subscribeToSaveResponse(this.nhaXuatBanService.update(this.nhaXuatBan));
        } else {
            this.subscribeToSaveResponse(this.nhaXuatBanService.create(this.nhaXuatBan));
        }
    }

    private subscribeToSaveResponse(result: Observable<HttpResponse<INhaXuatBan>>) {
        result.subscribe((res: HttpResponse<INhaXuatBan>) => this.onSaveSuccess(), (res: HttpErrorResponse) => this.onSaveError());
    }

    private onSaveSuccess() {
        this.isSaving = false;
        this.previousState();
    }

    private onSaveError() {
        this.isSaving = false;
    }
}
