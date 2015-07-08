package com.mnemotix.tuto.modules;

import com.google.inject.AbstractModule;
import com.mnemotix.tuto.services.DummyStudentStore;
import com.mnemotix.tuto.services.LenientRegistrar;
import com.mnemotix.tuto.services.Registrar;
import com.mnemotix.tuto.services.StudentStore;

public class SimpleModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(StudentStore.class).to(DummyStudentStore.class);
        bind(Registrar.class).to(LenientRegistrar.class);
    }
}
