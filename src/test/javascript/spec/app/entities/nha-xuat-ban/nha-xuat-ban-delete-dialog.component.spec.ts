/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { BookDemoTestModule } from '../../../test.module';
import { NhaXuatBanDeleteDialogComponent } from 'app/entities/nha-xuat-ban/nha-xuat-ban-delete-dialog.component';
import { NhaXuatBanService } from 'app/entities/nha-xuat-ban/nha-xuat-ban.service';

describe('Component Tests', () => {
    describe('NhaXuatBan Management Delete Component', () => {
        let comp: NhaXuatBanDeleteDialogComponent;
        let fixture: ComponentFixture<NhaXuatBanDeleteDialogComponent>;
        let service: NhaXuatBanService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [BookDemoTestModule],
                declarations: [NhaXuatBanDeleteDialogComponent]
            })
                .overrideTemplate(NhaXuatBanDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(NhaXuatBanDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(NhaXuatBanService);
            mockEventManager = fixture.debugElement.injector.get(JhiEventManager);
            mockActiveModal = fixture.debugElement.injector.get(NgbActiveModal);
        });

        describe('confirmDelete', () => {
            it('Should call delete service on confirmDelete', inject(
                [],
                fakeAsync(() => {
                    // GIVEN
                    spyOn(service, 'delete').and.returnValue(of({}));

                    // WHEN
                    comp.confirmDelete('123');
                    tick();

                    // THEN
                    expect(service.delete).toHaveBeenCalledWith('123');
                    expect(mockActiveModal.dismissSpy).toHaveBeenCalled();
                    expect(mockEventManager.broadcastSpy).toHaveBeenCalled();
                })
            ));
        });
    });
});
