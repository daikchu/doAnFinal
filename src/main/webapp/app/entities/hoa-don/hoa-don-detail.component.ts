import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IHoaDon } from 'app/shared/model/hoa-don.model';

@Component({
    selector: 'jhi-hoa-don-detail',
    templateUrl: './hoa-don-detail.component.html'
})
export class HoaDonDetailComponent implements OnInit {
    hoaDon: IHoaDon;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ hoaDon }) => {
            this.hoaDon = hoaDon;
        });
    }

    previousState() {
        window.history.back();
    }
}
