/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { BookDemoTestModule } from '../../../test.module';
import { KhuyenMaiDeleteDialogComponent } from 'app/entities/khuyen-mai/khuyen-mai-delete-dialog.component';
import { KhuyenMaiService } from 'app/entities/khuyen-mai/khuyen-mai.service';

describe('Component Tests', () => {
    describe('KhuyenMai Management Delete Component', () => {
        let comp: KhuyenMaiDeleteDialogComponent;
        let fixture: ComponentFixture<KhuyenMaiDeleteDialogComponent>;
        let service: KhuyenMaiService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [BookDemoTestModule],
                declarations: [KhuyenMaiDeleteDialogComponent]
            })
                .overrideTemplate(KhuyenMaiDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(KhuyenMaiDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(KhuyenMaiService);
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
