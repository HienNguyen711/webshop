package project.webshop.service.impl;

import org.springframework.stereotype.Component;
import project.webshop.model.api.Vk;
import project.webshop.service.VkService;

@Component
public class VkServiceImpl implements VkService {

    @Override
    public void openAuthorizationDialog() throws Exception {
        Vk.auth();
    }
}
