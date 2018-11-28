import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';

import { NgbActiveModal, NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { IQuangCao } from 'app/shared/model/quang-cao.model';
import { QuangCaoService } from './quang-cao.service';

@Component({
    selector: 'jhi-quang-cao-delete-dialog',
    templateUrl: './quang-cao-delete-dialog.component.html'
})
export class QuangCaoDeleteDialogComponent {
    quangCao: IQuangCao;

    constructor(private quangCaoService: QuangCaoService, public activeModal: NgbActiveModal, private eventManager: JhiEventManager) {}

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: string) {
        this.quangCaoService.delete(id).subscribe(response => {
            this.eventManager.broadcast({
                name: 'quangCaoListModification',
                content: 'Deleted an quangCao'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-quang-cao-delete-popup',
    template: ''
})
export class QuangCaoDeletePopupComponent implements OnInit, OnDestroy {
    private ngbModalRef: NgbModalRef;

    constructor(private activatedRoute: ActivatedRoute, private router: Router, private modalService: NgbModal) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ quangCao }) => {
            setTimeout(() => {
                this.ngbModalRef = this.modalService.open(QuangCaoDeleteDialogComponent as Component, { size: 'lg', backdrop: 'static' });
                this.ngbModalRef.componentInstance.quangCao = quangCao;
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
