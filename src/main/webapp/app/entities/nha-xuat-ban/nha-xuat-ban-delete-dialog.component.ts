import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { INhaXuatBan } from 'app/shared/model/nha-xuat-ban.model';
import { NhaXuatBanService } from './nha-xuat-ban.service';

@Component({
    selector: 'jhi-nha-xuat-ban-delete-dialog',
    templateUrl: './nha-xuat-ban-delete-dialog.component.html'
})
export class NhaXuatBanDeleteDialogComponent {
    nhaXuatBan: INhaXuatBan;

    constructor(private nhaXuatBanService: NhaXuatBanService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: string) {
        this.nhaXuatBanService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'nhaXuatBanListModification',
                content: 'Deleted an nhaXuatBan'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-nha-xuat-ban-delete-popup',
    template: ''
})
export class NhaXuatBanDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ nhaXuatBan }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(NhaXuatBanDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.nhaXuatBan = nhaXuatBan;
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
