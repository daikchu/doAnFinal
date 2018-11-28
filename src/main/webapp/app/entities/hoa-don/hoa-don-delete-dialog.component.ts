import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IHoaDon } from 'app/shared/model/hoa-don.model';
import { HoaDonService } from './hoa-don.service';

@Component({
    selector: 'jhi-hoa-don-delete-dialog',
    templateUrl: './hoa-don-delete-dialog.component.html'
})
export class HoaDonDeleteDialogComponent {
    hoaDon: IHoaDon;

    constructor(private hoaDonService: HoaDonService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: string) {
        this.hoaDonService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'hoaDonListModification',
                content: 'Deleted an hoaDon'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-hoa-don-delete-popup',
    template: ''
})
export class HoaDonDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ hoaDon }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(HoaDonDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.hoaDon = hoaDon;
                this.ngbModalRef.result.then(
                    result => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    },
                    reason => {
                        this.router.navigate([{ outlets: { popup: null } }], { replaceUrl: true, queryParamsHandling: 'merge' });
                        this.ngbModalRef = null;
                    }
                );
            }, 0);
        });
    }

    ngOnDestroy() {
        this.ngbModalRef = null;
    }
}
