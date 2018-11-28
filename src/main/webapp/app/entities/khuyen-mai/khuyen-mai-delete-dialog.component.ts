import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IKhuyenMai } from 'app/shared/model/khuyen-mai.model';
import { KhuyenMaiService } from './khuyen-mai.service';

@Component({
    selector: 'jhi-khuyen-mai-delete-dialog',
    templateUrl: './khuyen-mai-delete-dialog.component.html'
})
export class KhuyenMaiDeleteDialogComponent {
    khuyenMai: IKhuyenMai;

    constructor(private khuyenMaiService: KhuyenMaiService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: string) {
        this.khuyenMaiService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'khuyenMaiListModification',
                content: 'Deleted an khuyenMai'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-khuyen-mai-delete-popup',
    template: ''
})
export class KhuyenMaiDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ khuyenMai }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(KhuyenMaiDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.khuyenMai = khuyenMai;
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
