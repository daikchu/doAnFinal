/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, inject, fakeAsync, tick } from '@angular/core/testing';
import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { Observable, of } from 'rxjs';
import { JhiEventManager } from 'ng-jhipster';

import { BookDemoTestModule } from '../../../test.module';
import { CTDHDeleteDialogComponent } from 'app/entities/ctdh/ctdh-delete-dialog.component';
import { CTDHService } from 'app/entities/ctdh/ctdh.service';

describe('Component Tests', () => {
    describe('CTDH Management Delete Component', () => {
        let comp: CTDHDeleteDialogComponent;
        let fixture: ComponentFixture<CTDHDeleteDialogComponent>;
        let service: CTDHService;
        let mockEventManager: any;
        let mockActiveModal: any;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [BookDemoTestModule],
                declarations: [CTDHDeleteDialogComponent]
            })
                .overrideTemplate(CTDHDeleteDialogComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(CTDHDeleteDialogComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CTDHService);
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
