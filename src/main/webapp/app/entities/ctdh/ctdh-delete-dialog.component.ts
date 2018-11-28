import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { ICTDH } from 'app/shared/model/ctdh.model';
import { CTDHService } from './ctdh.service';

@Component({
    selector: 'jhi-ctdh-delete-dialog',
    templateUrl: './ctdh-delete-dialog.component.html'
})
export class CTDHDeleteDialogComponent {
    cTDH: ICTDH;

    constructor(private cTDHService: CTDHService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: string) {
        this.cTDHService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'cTDHListModification',
                content: 'Deleted an cTDH'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-ctdh-delete-popup',
    template: ''
})
export class CTDHDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ cTDH }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(CTDHDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.cTDH = cTDH;
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
