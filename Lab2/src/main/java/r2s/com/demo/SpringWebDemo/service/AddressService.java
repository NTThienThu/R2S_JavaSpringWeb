package r2s.com.demo.SpringWebDemo.service;

import r2s.com.demo.SpringWebDemo.dto.request.CreateAddressRequestDTO;
import r2s.com.demo.SpringWebDemo.dto.request.CreateProductRequestDTO;
import r2s.com.demo.SpringWebDemo.dto.response.AddressResponseDTO;

import java.util.ArrayList;
import java.util.List;

public class AddressService {
    public List<AddressResponseDTO> getListAddress(){
        List<AddressResponseDTO> addressList= new ArrayList<>();
        AddressResponseDTO address1= new AddressResponseDTO(1,"thu","123456789","aaa","aaa","aaa",true, true, false,1);
        AddressResponseDTO address2= new AddressResponseDTO(1,"thienthu","123456789","aaa","Kaaa","aaa",true, false, false,1);
        addressList.add(address1);
        addressList.add(address2);
        return addressList;
    }
}
