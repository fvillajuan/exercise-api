package com.demo.exercise_api.allomonde.config;

import com.demo.exercise_api.allomonde.api.AlloMondeControleur;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.csrf;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.jwt;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@WebMvcTest(controllers = AlloMondeControleur.class)
@AutoConfigureMockMvc
@Import(ConfigurationSecurite.class)
class ConfigurationSecuriteTest {

    private static final String SCOPE_VALIDE = "allo.droit";
    private static final String SCOPE_INVALIDE = "autre.droit";

    @Autowired
    private MockMvc mockMvc;

    @Test
    void direAllo_avecScopeValide_ok() throws Exception {
        mockMvc.perform(post("/api/allo")
                        .with(jwt().jwt(builder -> builder.claim("scope", SCOPE_VALIDE))))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void direAllo_avecScopeInvalide_accesInterdit() throws Exception {
        mockMvc.perform(post("/api/allo")
                        .with(jwt().jwt(builder -> builder.claim("scope", SCOPE_INVALIDE))))
                .andExpect(MockMvcResultMatchers.status().isForbidden());
    }

    @Test
    void direAllo_nonAuthentifie_accesNonAutorise() throws Exception {
        mockMvc.perform(post("/api/allo")
                        .with(csrf()))
                .andExpect(MockMvcResultMatchers.status().isUnauthorized());
    }
}
