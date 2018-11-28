import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IGioHang } from 'app/shared/model/gio-hang.model';

@Component({
    selector: 'jhi-gio-hang-detail',
    templateUrl: './gio-hang-detail.component.html'
})
export class GioHangDetailComponent implements OnInit {
    gioHang: IGioHang;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ gioHang }) => {
            this.gioHang = gioHang;
        });
    }

    previousState() {
        window.history.back();
    }
}
