/*
 * Copyright 2008 JRimum Project
 * 
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at http://www.apache.org/licenses/LICENSE-2.0 Unless required by
 * applicable law or agreed to in writing, software distributed under the
 * License is distributed on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS
 * OF ANY KIND, either express or implied. See the License for the specific
 * language governing permissions and limitations under the License.
 * 
 * Created at: 30/03/2008 - 18:17:54
 * 
 * ================================================================================
 * 
 * Direitos autorais 2008 JRimum Project
 * 
 * Licenciado sob a Licença Apache, Versão 2.0 ("LICENÇA"); você não pode usar
 * esse arquivo exceto em conformidade com a esta LICENÇA. Você pode obter uma
 * cópia desta LICENÇA em http://www.apache.org/licenses/LICENSE-2.0 A menos que
 * haja exigência legal ou acordo por escrito, a distribuição de software sob
 * esta LICENÇA se dará “COMO ESTÁ”, SEM GARANTIAS OU CONDIÇÕES DE QUALQUER
 * TIPO, sejam expressas ou tácitas. Veja a LICENÇA para a redação específica a
 * reger permissões e limitações sob esta LICENÇA.
 * 
 * Criado em: 30/03/2008 - 18:17:54
 * 
 */


package br.com.nordestefomento.jrimum.utilix;

import static br.com.nordestefomento.jrimum.utilix.ObjectUtil.isNotNull;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.log4j.Logger;

/**
 * 
 * Descrição:
 * 
 * 
 * @author <a href="http://gilmatryx.googlepages.com/">Gilmar P.S.L</a>
 * @author Misael Barreto
 * @author Rômulo Augusto
 * @author <a href="http://www.nordeste-fomento.com.br">Nordeste Fomento
 *         Mercantil</a>
 * 
 * @since 0.2
 * 
 * @version 0.2
 */
public class FileUtil {
	
	//TODO Criar Metodo que recebe um arquivo e coisas para verificar nele, como: (isVazio,Numero de linhas, etc)
	
	protected static final Logger LOG = Logger.getLogger(FileUtil.class);
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1227314921804015225L;

	private static final int EOF = -1;

	private static final int CARRIAGE_RETURN = 1;

	private static final int NEXT_LINE = 2;
	
	private static final String NEW_LINE = "\r\n";


	/**
	 * <p>
	 * Transforma um array de bytes em um arquivo.
	 * </p>
	 * 
	 * @param pathName
	 * @param bytes
	 * @return Objeto File com o conteúdo sendo o dos bytes
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public static File bytes2File(String pathName, byte[] bytes) throws FileNotFoundException, IOException {
		
		File f = null;
		
		if(isNotNull(pathName,"pathName") && isNotNull(bytes,"bytes")){
			
			f = new File(pathName);
			
			OutputStream out = new FileOutputStream(f);
			
			out.write(bytes);
			out.flush();
			out.close();
		}
		
		return f;
	}
	
	
	/**
	 * <p>
	 * Transforma um array de bytes em um <code>ByteArrayOutputStream</code>.
	 * </p>
	 * 
	 * @param bytes
	 * @return ByteArrayOutputStream ou null
	 * @throws IOException
	 * 
	 * @since 
	 */
	public static ByteArrayOutputStream bytes2Stream(byte[] bytes) throws IOException{
		
		ByteArrayOutputStream byteOut = null;
		
		if(isNotNull(bytes,"bytes")){
			
			byteOut = new ByteArrayOutputStream();
			
			byteOut.write(bytes);
		}

		return byteOut;
	}
}