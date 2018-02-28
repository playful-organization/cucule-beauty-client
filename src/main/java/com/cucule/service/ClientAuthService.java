package com.cucule.service;

import com.cucule.common.global.CuculeLole;
import com.cucule.dao.entity.ClientMaster;
import com.cucule.dao.repository.ClientMasterRepository;
import com.cucule.dao.repository.UserMasterRepository;
import com.cucule.service.input.AuthServiceInput;
import com.cucule.service.output.AuthServiceOutput;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientAuthService {

    @Autowired
    UserMasterRepository userMasterRepository;

    @Autowired
    ClientMasterRepository clientMasterRepository;

    public AuthServiceOutput checkAuth(AuthServiceInput input) {

        // clientLole ver
        List<ClientMaster> results = clientMasterRepository.findByLoginIdAndPassword(input.getLonginId(), input.getLoginPassword());
        AuthServiceOutput output = new AuthServiceOutput();
        if (results.size() == 1) {
            ClientMaster result = results.get(0);
            output.setWasApplied(true);
            output.setLonginId(result.getLoginId());
            output.setLoginPassword(result.getLoginPassword());
            output.setClientId(result.getClientId());
            output.setLole(CuculeLole.Client);
        } else {
            // ここにuserも混ぜる？
            output.setWasApplied(false);
        }

        return output;
    }

}
