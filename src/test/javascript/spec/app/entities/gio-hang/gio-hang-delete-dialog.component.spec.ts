/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { BookDemoTestModule } from '../../../test.module';
import { GioHangDeleteDialogComponent } from 'app/entities/gio-hang/gio-hang-delete-dialog.component';
import { GioHangService } from 'app/entities/gio-hang/gio-hang.service';

describe('Component Tests', () => {
    describe('GioHang Management Delete Component', () => {
        let comp: GioHangDeleteDialogComponent;
        let fixture: ComponentFixture<GioHangDeleteDialogComponent>;
        let service: GioHangService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [BookDemoTestModule],
                declarations: [GioHangDeleteDialogComponent]
            })
                .overrideTemplate(GioHangDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(GioHangDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(GioHangService);
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
