import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IKhuyenMai } from 'app/shared/model/khuyen-mai.model';

@Component({
    selector: 'jhi-khuyen-mai-detail',
    templateUrl: './khuyen-mai-detail.component.html'
})
export class KhuyenMaiDetailComponent implements OnInit {
    khuyenMai: IKhuyenMai;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ khuyenMai }) => {
            this.khuyenMai = khuyenMai;
        });
    }

    previousState() {
        window.history.back();
    }
}
