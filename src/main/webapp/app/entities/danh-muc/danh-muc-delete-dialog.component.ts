import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IDanhMuc } from 'app/shared/model/danh-muc.model';
import { DanhMucService } from './danh-muc.service';

@Component({
    selector: 'jhi-danh-muc-delete-dialog',
    templateUrl: './danh-muc-delete-dialog.component.html'
})
export class DanhMucDeleteDialogComponent {
    danhMuc: IDanhMuc;

    constructor(private danhMucService: DanhMucService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: string) {
        this.danhMucService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'danhMucListModification',
                content: 'Deleted an danhMuc'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-danh-muc-delete-popup',
    template: ''
})
export class DanhMucDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ danhMuc }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(DanhMucDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.danhMuc = danhMuc;
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
