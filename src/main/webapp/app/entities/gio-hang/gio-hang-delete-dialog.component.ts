import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IGioHang } from 'app/shared/model/gio-hang.model';
import { GioHangService } from './gio-hang.service';

@Component({
    selector: 'jhi-gio-hang-delete-dialog',
    templateUrl: './gio-hang-delete-dialog.component.html'
})
export class GioHangDeleteDialogComponent {
    gioHang: IGioHang;

    constructor(private gioHangService: GioHangService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: string) {
        this.gioHangService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'gioHangListModification',
                content: 'Deleted an gioHang'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-gio-hang-delete-popup',
    template: ''
})
export class GioHangDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ gioHang }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(GioHangDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.gioHang = gioHang;
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
