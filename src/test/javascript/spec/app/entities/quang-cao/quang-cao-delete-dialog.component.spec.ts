/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { BookDemoTestModule } from '../../../test.module';
import { QuangCaoDeleteDialogComponent } from 'app/entities/quang-cao/quang-cao-delete-dialog.component';
import { QuangCaoService } from 'app/entities/quang-cao/quang-cao.service';

describe('Component Tests', () => {
    describe('QuangCao Management Delete Component', () => {
        let comp: QuangCaoDeleteDialogComponent;
        let fixture: ComponentFixture<QuangCaoDeleteDialogComponent>;
        let service: QuangCaoService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [BookDemoTestModule],
                declarations: [QuangCaoDeleteDialogComponent]
            })
                .overrideTemplate(QuangCaoDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(QuangCaoDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(QuangCaoService);
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
