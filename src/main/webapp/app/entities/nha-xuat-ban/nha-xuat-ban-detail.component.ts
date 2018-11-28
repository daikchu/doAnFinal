import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { INhaXuatBan } from 'app/shared/model/nha-xuat-ban.model';

@Component({
    selector: 'jhi-nha-xuat-ban-detail',
    templateUrl: './nha-xuat-ban-detail.component.html'
})
export class NhaXuatBanDetailComponent implements OnInit {
    nhaXuatBan: INhaXuatBan;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ nhaXuatBan }) => {
            this.nhaXuatBan = nhaXuatBan;
        });
    }

    previousState() {
        window.history.back();
    }
}
