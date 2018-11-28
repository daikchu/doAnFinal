/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, fakeAsync, tick } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable, of } from 'rxjs';

import { BookDemoTestModule } from '../../../test.module';
import { CTDHUpdateComponent } from 'app/entities/ctdh/ctdh-update.component';
import { CTDHService } from 'app/entities/ctdh/ctdh.service';
import { CTDH } from 'app/shared/model/ctdh.model';

describe('Component Tests', () => {
    describe('CTDH Management Update Component', () => {
        let comp: CTDHUpdateComponent;
        let fixture: ComponentFixture<CTDHUpdateComponent>;
        let service: CTDHService;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [BookDemoTestModule],
                declarations: [CTDHUpdateComponent]
            })
                .overrideTemplate(CTDHUpdateComponent, '')
                .compileComponents();

            fixture = TestBed.createComponent(CTDHUpdateComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(CTDHService);
        });

        describe('save', () => {
            it('Should call update service on save for existing entity', fakeAsync(() => {
                // GIVEN
                const entity = new CTDH('123');
                spyOn(service, 'update').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.cTDH = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.update).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));

            it('Should call create service on save for new entity', fakeAsync(() => {
                // GIVEN
                const entity = new CTDH();
                spyOn(service, 'create').and.returnValue(of(new HttpResponse({ body: entity })));
                comp.cTDH = entity;
                // WHEN
                comp.save();
                tick(); // simulate async

                // THEN
                expect(service.create).toHaveBeenCalledWith(entity);
                expect(comp.isSaving).toEqual(false);
            }));
        });
    });
});
