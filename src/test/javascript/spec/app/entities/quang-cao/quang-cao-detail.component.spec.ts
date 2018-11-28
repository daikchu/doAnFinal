/* tslint:disable max-line-length */
import { ComponentFixture, TestBed } from '@angular/core/testing';
import { ActivatedRoute } from '@angular/router';
import { of } from 'rxjs';

import { BookDemoTestModule } from '../../../test.module';
import { QuangCaoDetailComponent } from 'app/entities/quang-cao/quang-cao-detail.component';
import { QuangCao } from 'app/shared/model/quang-cao.model';

describe('Component Tests', () => {
    describe('QuangCao Management Detail Component', () => {
        let comp: QuangCaoDetailComponent;
        let fixture: ComponentFixture<QuangCaoDetailComponent>;
        const route = ({ data: of({ quangCao: new QuangCao('123') }) } as any) as ActivatedRoute;

        beforeEach(() => {
            TestBed.configureTestingModule({
                imports: [BookDemoTestModule],
                declarations: [QuangCaoDetailComponent],
                providers: [{ provide: ActivatedRoute, useValue: route }]
            })
                .overrideTemplate(QuangCaoDetailComponent, '')
                .compileComponents();
            fixture = TestBed.createComponent(QuangCaoDetailComponent);
            comp = fixture.componentInstance;
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(comp.quangCao).toEqual(jasmine.objectContaining({ id: '123' }));
            });
        });
    });
});
