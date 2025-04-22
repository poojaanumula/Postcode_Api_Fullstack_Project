import { useState, useEffect } from 'react';
import axios from 'axios';
import Classes from './Home.module.scss';

interface Postcode {
  id: number;
  code: string;
}

interface Suburb {
  name: string;
  postcode: Postcode;
}

const Home = () => {
  const [searchTerm, setSearchTerm] = useState('');
  const [suburbs, setSuburbs] = useState<Suburb[]>([]);
  const [filteredResults, setFilteredResults] = useState<Suburb[]>([]);

  useEffect(() => {
    const fetchSuburbs = async () => {
      try {
        const response = await axios.get<Suburb[]>('http://localhost:8080/suburbs');
        setSuburbs(response.data);
      } catch (error) {
        console.error('Error fetching suburbs:', error);
      }
    };

    fetchSuburbs();
  }, []);

  const handleSearch = (e: React.FormEvent<HTMLFormElement>) => {
    e.preventDefault();

    const trimmed = searchTerm.trim().toLowerCase();
    if (!trimmed) {
      setFilteredResults([]);
      return;
    }

    const results = suburbs.filter((suburb) =>
      suburb.name.toLowerCase().includes(trimmed) ||
      suburb.postcode.code.includes(trimmed)
    );

    setFilteredResults(results);
    setSearchTerm(''); // Clear input after showing results
  };

  return (
    <div className={Classes.wrapper}>
      <h2 className={Classes.font}>
        Postcode Finder. Type a suburb or postcode and click Search:
      </h2>

      <form onSubmit={handleSearch} className={Classes.form}>
        <input
          type="text"
          placeholder="Search suburb or postcode"
          className={Classes.input}
          value={searchTerm}
          onChange={(e) => setSearchTerm(e.target.value)}
        />
        <button type="submit" className={Classes.button}>Search</button>
      </form>

      <ul className={Classes.resultList}>
        {filteredResults.map((item, index) => (
          <li key={index} className={Classes.resultItem}>
            <strong>Suburb:</strong> {item.name} — <strong>Postcode:</strong> {item.postcode.code}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default Home;
