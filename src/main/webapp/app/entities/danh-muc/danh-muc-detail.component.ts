import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IDanhMuc } from 'app/shared/model/danh-muc.model';

@Component({
    selector: 'jhi-danh-muc-detail',
    templateUrl: './danh-muc-detail.component.html'
})
export class DanhMucDetailComponent implements OnInit {
    danhMuc: IDanhMuc;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ danhMuc }) => {
            this.danhMuc = danhMuc;
        });
    }

    previousState() {
        window.history.back();
    }
}
