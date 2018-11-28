import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { IQuangCao } from 'app/shared/model/quang-cao.model';

@Component({
    selector: 'jhi-quang-cao-detail',
    templateUrl: './quang-cao-detail.component.html'
})
export class QuangCaoDetailComponent implements OnInit {
    quangCao: IQuangCao;

    constructor(private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ quangCao }) => {
            this.quangCao = quangCao;
        });
    }

    previousState() {
        window.history.back();
    }
}
