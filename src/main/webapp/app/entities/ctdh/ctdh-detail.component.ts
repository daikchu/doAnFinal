import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { ICTDH } from 'app/shared/model/ctdh.model';

@Component({
    selector: 'jhi-ctdh-detail',
    templateUrl: './ctdh-detail.component.html'
})
export class CTDHDetailComponent implements OnInit {
    cTDH: ICTDH;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ cTDH }) => {
            this.cTDH = cTDH;
        });
    }

    previousState() {
        window.history.back();
    }
}
