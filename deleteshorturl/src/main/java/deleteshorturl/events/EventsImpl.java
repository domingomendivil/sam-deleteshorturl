package deleteshorturl.events;

import deleteshorturl.dao.DAO;

public class EventsImpl implements Events{

	private final DAO dao;

	public EventsImpl(DAO dao){
		this.dao=dao;
	}

	@Override
	public void delete(String shortPath) {
		System.out.println("events shortpath "+shortPath);
		dao.deleteById(shortPath);
	}




}
