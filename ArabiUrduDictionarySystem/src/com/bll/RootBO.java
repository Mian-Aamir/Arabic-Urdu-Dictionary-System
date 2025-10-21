//package com.bll;
//
//import java.util.ArrayList;
//import java.util.List;
//import com.dal.IDataAccessFacade;
//import com.dto.RootDTO;
//
//public class RootBO implements IRootBO {
//
//	private final IDataAccessFacade data;
//
//	public RootBO(IDataAccessFacade data) {
//		this.data = data;
//	}
//
//	@Override
//	public boolean addRoot(RootDTO root) {
//		if (root == null || isBlank(root.getRootId()))
//			return false;
//		return data.addRoot(root);
//	}
//
//	@Override
//	public boolean updateRoot(RootDTO root) {
//		if (root == null || isBlank(root.getRootId()))
//			return false;
//		return data.updateRoot(root);
//	}
//
//	@Override
//	public boolean deleteRoot(String rootId) {
//		if (isBlank(rootId))
//			return false;
//		return data.deleteRoot(rootId);
//	}
//
//	@Override
//	public RootDTO getRootById(String rootId) {
//		if (isBlank(rootId))
//			return null;
//		return data.getRootById(rootId);
//	}
//
//	@Override
//	public List<RootDTO> getAllRoots() {
//		List<RootDTO> roots = data.getAllRoots();
//		return roots != null ? roots : new ArrayList<RootDTO>();
//	}
//
//	private static boolean isBlank(String s) {
//		return s == null || s.trim().isEmpty();
//	}
//}
